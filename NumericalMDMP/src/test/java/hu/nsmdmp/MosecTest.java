package hu.nsmdmp;

import org.junit.Test;

public class MosecTest {

	@Test
	public void test() {
		int NUMVAR = 2;
		int NUMCON = 3;
		int NUMANZ = 6;

		/* matrix indexek, ahol nem 0 az ertek */
		int asub[][] = { { 0, 1, 2 }, { 0, 1, 2 } };

		/* matrix ertekei, ha van 0, akkor az nem szerepel itt  */
		double aval[][] = { { -0.03, -0.15, 0.02 }, { -0.01, -0.1, 0.02 } };

		/* cel fuggveny */
		double c[] = { 100, 50 };

		double infinity = 0;

		/* hatarfeltetelek x-re */
		mosek.Env.boundkey bkx[] = { mosek.Env.boundkey.lo, mosek.Env.boundkey.lo };
		double blx[] = { 0.0, 0.0 };
		double bux[] = { +infinity, +infinity };

		/* hatarfeltetelek b vectorra */
		double blc[] = { -0.3, -2.25, -infinity };
		double buc[] = { +infinity, +infinity, 0.5 };
		mosek.Env.boundkey[] bkc = { mosek.Env.boundkey.lo, mosek.Env.boundkey.lo, mosek.Env.boundkey.up };

		mosek.Env env = null;
		mosek.Task task = null;

		// Make mosek environment. 
		env = new mosek.Env();

		// Initialize the environment.
		env.init();

		// Create a task object linked with the environment env.
		task = new mosek.Task(env, 0, 0);

		try {
			task.putmaxnumvar(NUMVAR);
			task.putmaxnumcon(NUMCON);
			task.putmaxnumanz(NUMANZ);

			task.append(mosek.Env.accmode.var, NUMVAR);
			task.append(mosek.Env.accmode.con, NUMCON);

			task.putcfix(0.0);

			for (int j = 0; j < NUMVAR; ++j) {
				/* Set the linear term c_j in the objective.*/
				task.putcj(j, c[j]);

				/* Set the bounds on variable j.
				   blx[j] <= x_j <= bux[j] */
				task.putbound(mosek.Env.accmode.var, j, bkx[j], blx[j], bux[j]);

				/* Input column j of A */
				task.putavec(mosek.Env.accmode.var, j, asub[j], aval[j]);
			}

			for (int i = 0; i < NUMCON; ++i) {
				task.putbound(mosek.Env.accmode.con, i, bkc[i], blc[i], buc[i]);
			}

			task.putobjsense(mosek.Env.objsense.maximize);

			mosek.Env.rescode r = task.optimize();
			if (mosek.Env.rescode.ok != r) {
				System.out.println(r);
			}

			double[] xx = new double[NUMVAR];
			mosek.Env.solsta solsta[] = new mosek.Env.solsta[1];
			mosek.Env.prosta prosta[] = new mosek.Env.prosta[1];
			/* Get status information about the solution */
			task.getsolutionstatus(mosek.Env.soltype.bas, prosta, solsta);
			task.getsolutionslice(mosek.Env.soltype.bas, // Basic solution.     
									mosek.Env.solitem.xx, // Which part of solution.
									0, // Index of first variable.
									NUMVAR, // Index of last variable+1 
									xx);

			switch (solsta[0]) {
			case optimal:
			case near_optimal:
				System.out.println("Optimal primal solution\n");
				for (int j = 0; j < NUMVAR; ++j)
					System.out.println("x[" + j + "]:" + xx[j]);
				break;
			case dual_infeas_cer:
			case prim_infeas_cer:
			case near_dual_infeas_cer:
			case near_prim_infeas_cer:
				System.out.println("Primal or dual infeasibility.\n");
				break;
			case unknown:
				System.out.println("Unknown solution status.\n");
				break;
			default:
				System.out.println("Other solution status");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
