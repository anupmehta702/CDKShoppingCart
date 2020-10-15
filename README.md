# CDKShoppingCart
Code to give discounts to user based on type

# Steps to run 
1. This project runs on JDK 1.8 
2. Run application.java
3. It would following output -
<br />Discounts for PREMIUM
<br />[Discount{lowRangeBillAmount=0, highRangeBillAmount=4000, discountPercentage=10, discountFor=PREMIUM}, Discount{lowRangeBillAmount=4000, highRangeBillAmount=8000, discountPercentage=15, discountFor=PREMIUM}, Discount{lowRangeBillAmount=8000, highRangeBillAmount=12000, discountPercentage=20, discountFor=PREMIUM}, Discount{lowRangeBillAmount=12000, highRangeBillAmount=2147483647, discountPercentage=30, discountFor=PREMIUM}]
<br />Discounts for REGULAR
<br />[Discount{lowRangeBillAmount=0, highRangeBillAmount=5000, discountPercentage=0, discountFor=REGULAR}, Discount{lowRangeBillAmount=5000, highRangeBillAmount=10000, discountPercentage=10, discountFor=REGULAR}, Discount{lowRangeBillAmount=10000, highRangeBillAmount=2147483647, discountPercentage=20, discountFor=REGULAR}]

 <br />Total bill for  - Customer{type=REGULAR, amount=5000} is --> 5000
 <br />Total bill for  - Customer{type=REGULAR, amount=10000} is --> 9500
 <br />Total bill for  - Customer{type=REGULAR, amount=15000} is --> 13500
 <br />
 <br />Total bill for  - Customer{type=PREMIUM, amount=4000} is --> 3600
 <br />Total bill for  - Customer{type=PREMIUM, amount=8000} is --> 7000
 <br />Total bill for  - Customer{type=PREMIUM, amount=12000} is --> 10200
 <br />Total bill for  - Customer{type=PREMIUM, amount=20000} is --> 15800

#Design Evolution
1. I started with Discounts ,thinking it would have lowlimit, highLimit and discountPercentage and it would be abstract class that would be extended by Discounts
This would mean Discounts would have list of Discount.
<br /> I was thinking that I would have two classes per customerType and add it in Bill Counter
2. But then I realized that I should start with lowest class in heirachy i.e Discount
Then I thought it should have it's own interface and later we can club with Discount
I thought it can be abstract as well which will help us define the minimum parameters
 <br /> Question is what to choose between abstract and interface.
3. After third commit I now feel we dont need abstract class or interface . 
4. Wrote separate methods to add discounts in Discount ,later refactored/
5. Deleted Discount interface as it is not needed
6. Moved range validation to customerDiscount
 
 Open question - 
 1. isDiscountValidFor should be called by default . (not required -since Discount interface is gone)
 2. Make Map as EnumMap in Discounts (done).
 3. Should we transfer method to check range in Discount ?(done)
 
