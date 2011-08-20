package hu.nsmdmp.mosek;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.vector.Vector;
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
	public static LPSolution optimizeMin(final Matrix matrix, final Vector b, final Vector c) throws MosekException {
		SparseMatrix sm = new SparseMatrix(matrix);

		return new LinearProgrammingEq(sm.aval, sm.asub, Converters.convert(b.getArray()), Converters.convert(c.getArray())).optimize(Env.objsense.minimize);
	}

	/**
	 * Maximalizalasa.
	 * 
	 */
	public static LPSolution optimizeMax(final Matrix matrix, final Vector b, final Vector c) throws MosekException {
		SparseMatrix sm = new SparseMatrix(matrix);

		return new LinearProgrammingEq(sm.aval, sm.asub, Converters.convert(b.getArray()), Converters.convert(c.getArray())).optimize(Env.objsense.maximize);
	}

	private LPSolution optimize(final Env.objsense objsense) throws MosekException {
		int NUMVAR = aval.length;
		int NUMCON = b.length;
		int NUMANZ = getNumANZ();

		Env env = null;
		Task task = null;

		// Make mosek environment. 
		env = new Env();

		// Initialize the environment.
		env.init();

		// Create a task object linked with the environment env.
		task = new Task(env, 0, 0);

		task.putmaxnumvar(NUMVAR);
		task.putmaxnumcon(NUMCON);
		task.putmaxnumanz(NUMANZ);

		task.append(Env.accmode.var, NUMVAR);
		task.append(Env.accmode.con, NUMCON);

		task.putcfix(0.0);

		for (int j = 0; j < NUMVAR; ++j) {
			/* Set the linear term c_j in the objective.*/
			task.putcj(j, c[j]);

			/* Set the bounds on variable j.
			   blx[j] <= x_j <= bux[j] */
			task.putbound(Env.accmode.var, j, Env.boundkey.lo, 0, +infinity);

			/* Input column j of A */
			task.putavec(Env.accmode.var, j, asub[j], aval[j]);
		}

		for (int i = 0; i < NUMCON; ++i) {
			/* Set the bounds on constraints.
			   blc[i] <= constraint i <= buc[i] */
			task.putbound(Env.accmode.con, i, Env.boundkey.fx, b[i], b[i]);
		}

		task.putobjsense(objsense);

//		task.writedata("MDMP.lp");

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

		LPSolution lpSolution = new LPSolution();
		lpSolution.x = xx;
		lpSolution.primalSolution = task.getprimalobj(Env.soltype.bas);
		lpSolution.basisIndexes=new int[NUMCON];
		task.initbasissolve(lpSolution.basisIndexes);
		//task.getprimalobj(arg0, arg1)
		//System.out.println(Arrays.toString(lpSolution.basisIndexes));

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
		case near_optimal:
//			System.out.println("Optimal primal solution.");
			break;
		case dual_infeas_cer:
		case near_dual_infeas_cer:
			throw new MosekException("Dual infeasibility.");
		case prim_infeas_cer:
		case near_prim_infeas_cer:
			throw new MosekException("Primal infeasibility.");
		case unknown:
			throw new MosekException("Unknown solution status.");
		default:
			throw new MosekException("Other solution status.");
		}
	}
}
