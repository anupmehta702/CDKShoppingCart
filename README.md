# CDKShoppingCart
Code to give discounts to user based on type

Evolution
1. I started with Discounts ,thinking it would have lowlimit, highLimit and discountPercentage and it would be abstract class that would be extended by Discounts
This would mean Discounts would have list of Discount.
<br /> I was thinking that I would have two classes per customerType and add it in Bill Counter
2. But I realized that I should start with lowest class in heirachy i.e Discount
Then I thought it should have it's own interface and later we can club with Discount
I thought it can be abstract as well which will help us define the minimum parameters
 <br /> Question is what to choose between abstract and interface.
3. After third commit I now feel we dont need abstract class or interface . 
 
 Open question - 
 1. isDiscountValidFor should be called by default .
 
