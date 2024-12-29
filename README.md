the main idea is to modify input BigDecimal to scale 2, take an absolute value,
and then use plain string to start our logic

we know that last three symbols are the separator and two digits, 
so, right at the beginning we can define the need of the separator and the fractional part:

1. checking two last digits
   if both of them are '0' - we don't add separator and these digits
   for an opposite situation - we append the separator and those digits in the end 
2. analyzing the integer part to define how many grouping separators we need,
   we count an integer part length
   basically, it's {number.length - 3} (cause 3 = separator + 2 fractional digits)
   then we need to calculate a starting index for the first grouping separator
   
   as soon as we know that index, and we have reached out starting index, 
   it means that another separator will be put after next three elements,
   until we reach the last integer digit

   for instance:
   123456 has 6 digits, 6 % 3 = 0
   1234 has 4 digits, 4 % 3 = 1
   1234567 has 7 digits, 7 % 3 = 1
   12 has 2 digits, 2 % 3 = 0

   if our result is 0, we just add 3 right from the start
   in another case we save starting index value

3. iterating through the integer part
   
   firstly, we check our grouping separator index:
   when we reach that index, we will put separator to the builder, and then add 3 to that number
   it will happen every 3 digit until we reach out the end of the integer part

   and then we just append current digit