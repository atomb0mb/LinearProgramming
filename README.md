# LinearProgramming - An AI assignment
Learning linear programming through the use of the Apache Common Math Library. Commons Math is a collection of mathematics and statistics components that are not available by default in the Java programming language. Using linear programming to calculate the optimal result of each business model.

## ShoesMaker
You are small shoe maker. 

You produce four types of products: flats which earn $20 revenue, heels which earn $30 revenue, wedges which earn $25 revenue, and sandals which earn $5 revenue.

A pair of flats require 2 square feet of leather.

A pair of heels require 2.8 square feet of leather and 1.2 lbs of cork.

A pair of wedges require 1.2 square feet of leather and 2 lbs of cork.

A pair of sandals require 0.8 square feet of leather and 1.5 lbs rubber.

In addition, you must produce at least 10 of each type of shoe.

Because of past demand patterns, you should make 2 pairs of flats for each pair of heels manufactured. 

You have 1500 square feet of leather, 500 lbs of rubber, and 200 lbs of cork.

For testing purpose, the maximized objective function value is 12,416.67.

The double array values should be returned as such => [ optimized shoe sales value (12416.67), # of flats sold, # of heels sold, # of wedges sold, # of sandals sold ]



## BakeryManager
You are the purchasing manager of a bakery that operates on the local level. 

You produce four types of products: scones which earn $1 revenue, bagels which earn $2 revenue, donuts which earn $0.25 revenue, and cookies which earn $0.75 revenue.

A scone requires 0.05 lbs butter, 0.2 lbs flour, 0.25 eggs, 0.1 lbs sugar, and 0.02 gallons of milk.

A bagel requires 0.15 lbs butter, 0.25 lbs flour, 0.1 eggs, 0.05 lbs sugar, and 0.01 gallons of milk.

A donut requires 0.05 lbs butter, 0.08 lbs flour, 0.2 eggs, 0.15 lbs sugar, and 0.15 gallons of milk.

A cookie requires 0.15 lbs butter, 0.3 lbs flour, 0.05 eggs, 0.12 lbs sugar, and 0.05 gallons of milk.

To have enough items on display and to keep the bakers busy, you must produce at least 100 of each.

However, since these are perishable goods, we seldom manage to sell more than 500 of each before the remainder go bad, so don’t make more than 500 of any item. Because of past demand patterns, you should make 2 donuts for each bagel and 3 cookies for each scone. You have 130 lbs of butter. You have 300 lbs of flour. You have 180 eggs. You have 160 lbs of sugar. You have 100 gallons of milk.

For testing purpose, the maximized objective function value is 1062.

The double array values should be returned as such => [ optimized baked good sales value (1062), # of scones sold, # of bagels sold, # of donuts sold, # of cookies sold ]


## CoffeeShopOwner

You own the local chic coffee shop near NC State's campus. 

You sell three types of beverages: lattes which earn $2.6 revenue, cappuccinos which earn $1.3 revenue, and macchiatos which earn $3.0 revenue.

A latte requires 2oz of espresso coffee and 6oz of steamed milk.

A cappuccino requires 2oz of espresso coffee, 2oz of steamed milk, and 2oz of foamed milk.

A macchiato requires 2oz of espresso coffee and 4oz of milk foam.

You can make up to 2000oz of espresso coffee a day. You also have 5000oz of milk.

Before solving this linear problem, you first need to determine an optimal amount of milk to use for steaming and foaming.

One ounce (1oz) of milk produces 0.8 oz of steamed milk (accounting for evaporation).

One ounce (1oz) of milk produces 0.5 oz of milk foam (accounting for evaporation).

As mentioned earlier, you only have 5000oz of milk. For each unit of milk foam produced, you should make three times as much steamed milk.

Collectively, the maximized value for steamed and foamed milk produced is 3625oz. You should get 3625 from the getValue() method. However, the getPoint() method returns the number of UNITS, not ounces, of each type of milk produced.

Imagine if you had 1000oz of milk and only needed to produced steamed milk. The maximized value (getValue) would return 800; however, when you call getPoint()[0], you'll see 1000. You can read this as "1000 units of 0.8oz of steamed milk should be produced". In this situation, you now have 1000 x 0.8, or 800oz, of steamed milk to use. When transitioning from the milk production linear inequalities to the coffee shop inequalities, make sure to calculate your steamed and foamed milk into ounces.

From here, use the values of steamed and foamed milk to determine the optimal production of coffees. The maximized value for coffee sales is 1768.75.

The double array values should be returned as such => [ optimized coffee sales value (1768.75), # of lattes sold, # of cappuccinos sold, # of macchiatos sold ]
