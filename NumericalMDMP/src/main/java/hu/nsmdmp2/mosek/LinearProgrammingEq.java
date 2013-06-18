package hu.nsmdmp2.mosek;

import static hu.nsmdmp2.utils.Converters.toPrimitiveDoubleArray;
import hu.nsmdmp2.numerics.Value;
import hu.nsmdmp2.numerics.matrix.SparseMatrix;
import mosek.Env;
import mosek.MosekException;
import mosek.Task;

/**
 * Subject: Ax = b, x >= 0
 * 
 * min/max cx.
 * 
 * 
 */

class msgclass extends mosek.Stream {
	public msgclass() {
		super();
	}

	public void stream(String msg) {
		System.out.print(msg);
	}
}

public final class LinearProgrammingEq {

	private double infinity = 0;

	/**
	 * A matrix non-zero elemei;
	 */
	private final double aval[][];

	/**
	 * A matrix non-zero sor indexei;
	 */
	private final int asub[][];

	/**
	 * Celfuggveny egyutthatoi.
	 */
	private final double c[];

	/**
	 * Ax=b.
	 */
	private final double b[];

	/**
	 * Hiden.
	 * 
	 */
	private LinearProgrammingEq(final double aval[][], final int asub[][], final double b[], final double c[]) {
		this.aval = aval;
		this.asub = asub;
		this.b = b;
		this.c = c;
	}

	/**
	 * Minimalizalas.
	 * 
	 */
	public static <T> LPSolution optimizeMin(final Value[][] M, final Value[] b, final Value[] c) throws MosekException {
		SparseMatrix sm = new SparseMatrix(M);

		return new LinearProgrammingEq(sm.aval, sm.asub, toPrimitiveDoubleArray(b), toPrimitiveDoubleArray(c)).optimize(Env.objsense.minimize);
	}

	public static <T> LPSolution optimizeMin(final SparseMatrix SM, final Value[] b, final Value[] c) throws MosekException {
		return new LinearProgrammingEq(SM.aval, SM.asub, toPrimitiveDoubleArray(b), toPrimitiveDoubleArray(c)).optimize(Env.objsense.minimize);
	}

	/**
	 * Maximalizalasa.
	 * 
	 */
	public static <T> LPSolution optimizeMax(final Value[][] M, final Value[] b, final Value[] c) throws MosekException {
		SparseMatrix sm = new SparseMatrix(M);

		return new LinearProgrammingEq(sm.aval, sm.asub, toPrimitiveDoubleArray(b), toPrimitiveDoubleArray(c)).optimize(Env.objsense.maximize);
	}

	public static <T> LPSolution optimizeMax(final SparseMatrix SM, final Value[] b, final Value[] c) throws MosekException {
		return new LinearProgrammingEq(SM.aval, SM.asub, toPrimitiveDoubleArray(b), toPrimitiveDoubleArray(c)).optimize(Env.objsense.maximize);
	}

	private LPSolution optimize(final Env.objsense objsense) throws MosekException {
		int NUMVAR = aval.length;
		int NUMCON = b.length;
		int NUMANZ = getNumANZ();

		Env env = null;
		Task task = null;

		// Make mosek environment.
		env = new Env();

		// Direct the env log stream to the user specified
		// method env_msg_obj.stream
		msgclass env_msg_obj = new msgclass();
		env.set_Stream(mosek.Env.streamtype.log, env_msg_obj);
		env.set_Stream(mosek.Env.streamtype.msg, env_msg_obj);

		// Initialize the environment.
		env.init();

		// Create a task object linked with the environment env.
		task = new Task(env, 0, 0);

		// Directs the log task stream to the user specified
		// method task_msg_obj.stream
		// msgclass task_msg_obj = new msgclass();
		// task.set_Stream(mosek.Env.streamtype.msg, task_msg_obj);
		// task.solutionsummary(mosek.Env.streamtype.msg);

		// task.putdouparam(mosek.Env.dparam.basis_tol_s, 1.0e-9);
		// task.putdouparam(mosek.Env.dparam.basis_tol_x, 1.0e-9);
		// task.putdouparam(mosek.Env.dparam.basis_rel_tol_s, 1.0e-6);
		// task.putintparam(mosek.Env.iparam.infeas_report_auto, 1);

		task.putmaxnumvar(NUMVAR);
		task.putmaxnumcon(NUMCON);
		task.putmaxnumanz(NUMANZ);

		task.append(Env.accmode.var, NUMVAR);
		task.append(Env.accmode.con, NUMCON);

		task.putcfix(0.0);

		for (int j = 0; j < NUMVAR; ++j) {
			/* Set the linear term c_j in the objective. */
			task.putcj(j, c[j]);

			/*
			 * Set the bounds on variable j. blx[j] <= x_j <= bux[j]
			 */
			task.putbound(Env.accmode.var, j, Env.boundkey.lo, 0, +infinity);

			/* Input column j of A */
			task.putavec(Env.accmode.var, j, asub[j], aval[j]);
		}

		for (int i = 0; i < NUMCON; ++i) {
			/*
			 * Set the bounds on constraints. blc[i] <= constraint i <= buc[i]
			 */
			task.putbound(Env.accmode.con, i, Env.boundkey.fx, b[i], b[i]);
		}

		task.putobjsense(objsense);

		task.writedata("MDMP.mps");

		task.putintparam(mosek.Env.iparam.optimizer, mosek.Env.optimizertype.dual_simplex.getValue());

		Env.rescode r = task.optimize();
		if (Env.rescode.ok != r) {
			System.out.println(r);
		}

		double[] xx = new double[NUMVAR];
		Env.solsta solsta[] = new Env.solsta[1];
		Env.prosta prosta[] = new Env.prosta[1];

		/* Get status information about the solution */
		task.getsolutionstatus(Env.soltype.bas, prosta, solsta);
		task.getsolutionslice(Env.soltype.bas, Env.solitem.xx, 0, NUMVAR, xx);

		checkSolutionStatus(solsta);

		LPSolution lpSolution = new LPSolution(xx, task.getprimalobj(Env.soltype.bas), new int[NUMCON], solsta[0]);
		task.initbasissolve(lpSolution.basisIndexes);
		// task.getprimalobj(arg0, arg1)
		// System.out.println(Arrays.toString(lpSolution.basisIndexes));

		return lpSolution;
	}

	private int getNumANZ() {
		int n = 0;
		for (int[] row : asub) {
			n += row.length;
		}

		return n;
	}

	private void checkSolutionStatus(final Env.solsta solsta[]) throws MosekException {
		switch (solsta[0]) {
		case optimal:
			System.out.println("The solution is optimal.");
			break;
		case near_optimal:
			System.out.println("The solution is nearly optimal.");
			break;
		case dual_infeas_cer:
			System.out.println("The solution is a certificate of dual infeasibility.");
			break;
		// throw new
		// MosekException("The solution is a certificate of dual infeasibility.");
		case near_dual_infeas_cer:
			System.out.println("The solution is almost a certificate of dual infeasibility.");
			break;
		// throw new
		// MosekException("The solution is almost a certificate of dual infeasibility.");
		case prim_infeas_cer:
			System.out.println("The solution is a certificate of primal infeasibility.");
			break;
		// throw new
		// MosekException("The solution is a certificate of primal infeasibility.");
		case near_prim_infeas_cer:
			System.out.println("The solution is almost a certificate of primal infeasibility.");
			break;
		// throw new
		// MosekException("The solution is almost a certificate of primal infeasibility.");
		case dual_feas:
			System.out.println("The solution is dual feasible.");
			break;
		case prim_feas:
			System.out.println("The solution is primal feasible.");
			break;
		case prim_and_dual_feas:
			System.out.println("The solution is both primal and dual feasible.");
			break;
		case unknown:
			throw new MosekException("Status of the solution is unknown.");
		default:
			throw new MosekException("Other solution status.");
		}
	}
}
