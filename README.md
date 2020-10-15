# CDKShoppingCart
Code to give discounts to user based on type

# Steps to run 
1. This project runs on JDK 1.8 .Install JDK 1.8
2. Import the project using below command -
<br />git clone https://github.com/anupmehta702/CDKShoppingCart.git 
3. Run application.java from Eclipse/IntelliJ
4. It would print following output -

<br />-- Existing Discounts --

<br />Discounts for PREMIUM
<br />[CustomerDiscount{lowRangeBillAmount=0, highRangeBillAmount=4000, discountPercentage=10, discountFor=PREMIUM}, 
<br />CustomerDiscount{lowRangeBillAmount=4000, highRangeBillAmount=8000, discountPercentage=15, discountFor=PREMIUM}, 
<br />CustomerDiscount{lowRangeBillAmount=8000, highRangeBillAmount=12000, discountPercentage=20, discountFor=PREMIUM}, 
<br />CustomerDiscount{lowRangeBillAmount=12000, highRangeBillAmount=2147483647, discountPercentage=30, discountFor=PREMIUM}]

<br />Discounts for REGULAR
<br />[CustomerDiscount{lowRangeBillAmount=0, highRangeBillAmount=5000, discountPercentage=0, discountFor=REGULAR}, 
<br />CustomerDiscount{lowRangeBillAmount=5000, highRangeBillAmount=10000, discountPercentage=10, discountFor=REGULAR}, 
<br />CustomerDiscount{lowRangeBillAmount=10000, highRangeBillAmount=2147483647, discountPercentage=20, discountFor=REGULAR}]

<br />------------------------

 <br />Total bill for  - Customer{type=REGULAR, amount=5000} is --> 5000
 <br />Total bill for  - Customer{type=REGULAR, amount=10000} is --> 9500
 <br />Total bill for  - Customer{type=REGULAR, amount=15000} is --> 13500
 <br />
 <br />Total bill for  - Customer{type=PREMIUM, amount=4000} is --> 3600
 <br />Total bill for  - Customer{type=PREMIUM, amount=8000} is --> 7000
 <br />Total bill for  - Customer{type=PREMIUM, amount=12000} is --> 10200
 <br />Total bill for  - Customer{type=PREMIUM, amount=20000} is --> 15800

