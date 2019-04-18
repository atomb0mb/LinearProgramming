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

public class NgChee_ProblemSet03 {

    public static double[] linearProgram_Coffee () {

        /**
         * Before solving this linear problem, you first need to determine an
         * optimal amount of milk to use for steaming and foaming.
         *
         * One ounce (1oz) of milk produces 0.8 oz of steamed milk (accounting
         * for evaporation). One ounce (1oz) of milk produces 0.5 oz of milk
         * foam (accounting for evaporation). As mentioned earlier, you only
         * have 5000oz of milk.
         *
         */

        final LinearObjectiveFunction milkproduced = new LinearObjectiveFunction( new double[] { 0.8, 0.5 }, 0 );
        final Collection<LinearConstraint> cons2 = new ArrayList<LinearConstraint>();

        /** You also have 5000oz of milk. */
        cons2.add( new LinearConstraint( new double[] { 1, 1 }, Relationship.LEQ, 5000 ) );

        /**
         * For each unit of milk foam produced, you should make three times as
         * much steamed milk.
         *
         **/
        cons2.add( new LinearConstraint( new double[] { 0, 3 }, 0, Relationship.EQ, new double[] { 1, 0 }, 0 ) );

        PointValuePair milks = null;
        final LinearConstraintSet constraintSet1 = new LinearConstraintSet( cons2 );
        try {
            milks = new SimplexSolver().optimize( milkproduced, constraintSet1, GoalType.MAXIMIZE,
                    new NonNegativeConstraint( true ) );
        }
        catch ( final Exception e ) {
            // System.out.println( "no solution fulfilling the constraints can
            // be found" );

            return new double[0];
        }
        // System.out.println( milks.getPoint()[0] * 0.8 + " Steamed milk" );
        // System.out.println( milks.getPoint()[1] * 0.5 + " Foamed milk\n" );
        /**
         * Three types of beverages: lattes which earn $2.6 revenue, cappuccinos
         * which earn $1.3 revenue, and macchiatos which earn $3.0 revenue.
         **/
        final LinearObjectiveFunction f = new LinearObjectiveFunction( new double[] { 2.6, 1.3, 3.0 }, 0 );
        final Collection<LinearConstraint> contraints = new ArrayList<LinearConstraint>();
        /**
         * You can make up to 2000oz of espresso coffee a day. You also have
         * 5000oz of milk. A latte requires 2oz of espresso coffee and 6oz of
         * steamed milk. A cappuccino requires 2oz of espresso coffee, 2oz of
         * steamed milk, and 2oz of foamed milk. A macchiato requires 2oz of
         * espresso coffee and 4oz of milk foam.
         **/

        /** Espresso coffee **/
        contraints.add( new LinearConstraint( new double[] { 2, 2, 2 }, Relationship.LEQ, 2000 ) );
        /** Steamed milk **/
        contraints.add( new LinearConstraint( new double[] { 6, 2, 0 }, Relationship.LEQ, milks.getPoint()[0] * 0.8 ) );
        /** foam milk **/
        contraints.add( new LinearConstraint( new double[] { 0, 2, 4 }, Relationship.LEQ, milks.getPoint()[1] * 0.5 ) );

        /**
         * Collectively, the maximized value for steamed and foamed milk
         * produced is 3625oz. You should get 3625 from the getValue() method.
         * However, the getPoint() method returns the number of UNITS, not
         * ounces, of each type of milk produced. Imagine if you had 1000oz of
         * milk and only needed to produced steamed milk. The maximized value
         * (getValue) would return 800; however, when you call getPoint()[0],
         * you'll see 1000. You can read this as "1000 units of 0.8oz of steamed
         * milk should be produced". In this situation, you now have 1000 x 0.8,
         * or 800oz, of steamed milk to use. When transitioning from the milk
         * production linear inequalities to the coffee shop inequalities, make
         * sure to calculate your steamed and foamed milk into ounces.
         *
         **/

        // create and run solver

        final double[] optimal = new double[] { 0, 0, 0, 0 };

        PointValuePair solution = null;
        final LinearConstraintSet constraintSet = new LinearConstraintSet( contraints );
        try {
            solution = new SimplexSolver().optimize( f, constraintSet, GoalType.MAXIMIZE,
                    new NonNegativeConstraint( true ) );
        }
        catch ( final Exception e ) {
            // System.out.println( "no solution fulfilling the constraints can
            // be found" );

            return new double[0];
        }

        if ( solution != null ) {
            // Get solution
            final double sol = solution.getValue();

            optimal[0] = sol;

            for ( int i = 0; i < solution.getPoint().length; i++ ) {
                final int variable = (int) solution.getPoint()[i];

                optimal[i + 1] = variable;
            }

        }

        return optimal;

    }

    public static double[] linearProgram_Shoes () {

        /**
         * Four types: flats which earn $20 revenue, heels which earn $30 //
         * revenue, wedges which earn $25 revenue, and sandals which earn $5
         * revenue.
         **/
        final LinearObjectiveFunction f = new LinearObjectiveFunction( new double[] { 20, 30, 25, 5 }, 0 );

        /**
         * You have 1500 square feet of leather, 500 lbs of rubber, and 200 lbs
         * of cork.
         **/
        final Collection<LinearConstraint> contraints = new ArrayList<LinearConstraint>();

        /** Leather **/
        contraints.add( new LinearConstraint( new double[] { 2, 2.8, 1.2, 0.8 }, Relationship.LEQ, 1500 ) );
        /** Rubber **/
        contraints.add( new LinearConstraint( new double[] { 0, 0, 0, 1.5 }, Relationship.LEQ, 500 ) );
        /** Cork **/
        contraints.add( new LinearConstraint( new double[] { 0, 1.2, 2, 0 }, Relationship.LEQ, 200 ) );

        /** In addition, you must produce at least 10 of each type of shoe. **/
        contraints.add( new LinearConstraint( new double[] { 1, 0, 0, 0 }, Relationship.GEQ, 10 ) );
        contraints.add( new LinearConstraint( new double[] { 0, 1, 0, 0 }, Relationship.GEQ, 10 ) );
        contraints.add( new LinearConstraint( new double[] { 0, 0, 1, 0 }, Relationship.GEQ, 10 ) );
        contraints.add( new LinearConstraint( new double[] { 0, 0, 0, 1 }, Relationship.GEQ, 10 ) );

        /**
         * Because of past demand patterns, you should make 2 pairs of flats for
         * each pair of heels manufactured.
         **/
        contraints.add( new LinearConstraint( new double[] { 0.5, 0, 0, 0 }, 0, Relationship.EQ,
                new double[] { 0, 1, 0, 0 }, 0 ) );

        // create and run solver

        final double[] optimal = new double[] { 0, 0, 0, 0, 0 };

        PointValuePair solution = null;
        final LinearConstraintSet constraintSet = new LinearConstraintSet( contraints );
        try {
            solution = new SimplexSolver().optimize( f, constraintSet, GoalType.MAXIMIZE,
                    new NonNegativeConstraint( true ) );
        }
        catch ( final Exception e ) {
            // System.out.println( "no solution fulfilling the constraints can
            // be found" );

            return new double[0];
        }

        if ( solution != null ) {
            // Get solution
            final double sol = solution.getValue();

            optimal[0] = sol;

            for ( int i = 0; i < solution.getPoint().length; i++ ) {
                final int variable = (int) solution.getPoint()[i];

                optimal[i + 1] = variable;
            }

        }

        return optimal;

    }

    static double[] linearProgram_Bakery () {
        /**
         * four types of products: scones which earn $1 revenue, bagels which
         * earn $2 revenue, donuts which earn $0.25 revenue, and cookies which
         * earn $0.75 revenue.
         **/
        final LinearObjectiveFunction f = new LinearObjectiveFunction( new double[] { 1, 2, 0.25, 0.75 }, 0 );

        /**
         * You have 130 lbs of butter. You have 300 lbs of flour. You have 180
         * eggs. You have 160 lbs of sugar. You have 100 gallons of milk.
         **/
        final Collection<LinearConstraint> contraints = new ArrayList<LinearConstraint>();
        /** butter **/
        contraints.add( new LinearConstraint( new double[] { 0.05, 0.15, 0.05, 0.15 }, Relationship.LEQ, 130 ) );
        /** flour **/
        contraints.add( new LinearConstraint( new double[] { 0.2, 0.25, 0.08, 0.3 }, Relationship.LEQ, 300 ) );
        /** eggs **/
        contraints.add( new LinearConstraint( new double[] { 0.25, 0.1, 0.2, 0.05 }, Relationship.LEQ, 180 ) );
        /** sugar **/
        contraints.add( new LinearConstraint( new double[] { 0.1, 0.05, 0.15, 0.12 }, Relationship.LEQ, 160 ) );
        /** milk **/
        contraints.add( new LinearConstraint( new double[] { 0.02, 0.01, 0.15, 0.05 }, Relationship.LEQ, 100 ) );

        /**
         * However, since these are perishable goods, we seldom manage to sell
         * more than 500 of each before the remainder go bad, so don’t make more
         * than 500 of any item
         **/
        contraints.add( new LinearConstraint( new double[] { 1, 0, 0, 0 }, Relationship.LEQ, 500 ) );
        contraints.add( new LinearConstraint( new double[] { 0, 1, 0, 0 }, Relationship.LEQ, 500 ) );
        contraints.add( new LinearConstraint( new double[] { 0, 0, 1, 0 }, Relationship.LEQ, 500 ) );
        contraints.add( new LinearConstraint( new double[] { 0, 0, 0, 1 }, Relationship.LEQ, 500 ) );

        /**
         * To have enough items on display and to keep the bakers busy, you must
         * produce at least 100 of each.
         **/
        contraints.add( new LinearConstraint( new double[] { 1, 0, 0, 0 }, Relationship.GEQ, 100 ) );
        contraints.add( new LinearConstraint( new double[] { 0, 1, 0, 0 }, Relationship.GEQ, 100 ) );
        contraints.add( new LinearConstraint( new double[] { 0, 0, 1, 0 }, Relationship.GEQ, 100 ) );
        contraints.add( new LinearConstraint( new double[] { 0, 0, 0, 1 }, Relationship.GEQ, 100 ) );

        /** 2 donuts for each bagel **/
        contraints.add( new LinearConstraint( new double[] { 0, 0, 1, 0 }, 0, Relationship.EQ,
                new double[] { 0, 2, 0, 0 }, 0 ) );

        /** 3 cookies for each scone **/
        contraints.add( new LinearConstraint( new double[] { 0, 0, 0, 1 }, 0, Relationship.EQ,
                new double[] { 3, 0, 0, 0 }, 0 ) );

        // create and run solver

        final double[] optimal = new double[] { 0, 0, 0, 0, 0 };

        PointValuePair solution = null;
        final LinearConstraintSet constraintSet = new LinearConstraintSet( contraints );
        try {
            solution = new SimplexSolver().optimize( f, constraintSet, GoalType.MAXIMIZE,
                    new NonNegativeConstraint( true ) );
        }
        catch ( final Exception e ) {
            // System.out.println( "no solution fulfilling the constraints can
            // be found" );

            return new double[0];
        }

        if ( solution != null ) {
            // Get solution
            final double sol = solution.getValue();

            optimal[0] = sol;

            for ( int i = 0; i < solution.getPoint().length; i++ ) {
                final int variable = (int) solution.getPoint()[i];

                optimal[i + 1] = variable;
            }

        }

        return optimal;

    }

    public static void main ( final String[] args ) {

        final double[] shoes = linearProgram_Shoes();
        System.out.println( "Optimized shoe sales values(" + String.format( "%.2f", shoes[0] ) + "), " + shoes[1]
                + " of flats sold, " + shoes[2] + " of heels sold, " + shoes[3] + " of wedges sold, " + shoes[4]
                + " of sandals sold\n" );

        final double[] bakery = linearProgram_Bakery();
        System.out.println( "Optimized baked good sales values(" + String.format( "%.2f", bakery[0] ) + "), "
                + bakery[1] + " of scones sold, " + bakery[2] + " of bagels sold, " + bakery[3] + " of donuts sold, "
                + bakery[4] + " of cookies sold\n" );

        final double[] coffee = linearProgram_Coffee();
        System.out.println( "Optimized coffee good sales values(" + String.format( "%.2f", coffee[0] ) + "), "
                + coffee[1] + " of lattes sold, " + coffee[2] + " of cappuccinos sold, " + coffee[3]
                + " of macchiatos sold" );
    }

}
