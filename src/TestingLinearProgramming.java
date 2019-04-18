import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class TestingLinearProgramming {

    public static void main ( final String[] args ) {
        // describe the optimization problem
        final LinearObjectiveFunction f = new LinearObjectiveFunction( new double[] { 5, 10 }, 0 );

        final Collection<LinearConstraint> contraints = new ArrayList<LinearConstraint>();
        contraints.add( new LinearConstraint( new double[] { 0, 3.3 }, Relationship.LEQ, 100 ) );
        contraints.add( new LinearConstraint( new double[] { 1.8, 0 }, Relationship.LEQ, 200 ) );
        contraints.add( new LinearConstraint( new double[] { 3.5, 2.1 }, Relationship.LEQ, 300 ) );
        contraints.add( new LinearConstraint( new double[] { 0.5, 0 }, 0, Relationship.EQ, new double[] { 0, 1 }, 0 ) );

        // create and run solver
        PointValuePair solution = null;
        final LinearConstraintSet constraintSet = new LinearConstraintSet( contraints );
        try {
            solution = new SimplexSolver().optimize( f, constraintSet, GoalType.MAXIMIZE,
                    new NonNegativeConstraint( true ) );
        }
        catch ( final Exception e ) {
            System.out.println( "no solution fulfilling the constraints can be found" );
        }

        if ( solution != null ) {
            // Get solution
            final double sol = solution.getValue();
            System.out.println( "Optimal: " + sol );

            for ( int i = 0; i < solution.getPoint().length; i++ ) {
                final int variable = (int) solution.getPoint()[i];
                System.out.print( variable + "\t" );
            }
            System.out.println( "" );
        }
    }
}
