# Remarks  
##
- It is not specified whether a student can attend two
workshops if one workshop begins at the same 
time the other ends(and they are held in the same
room). However, it comes natural to say that this case
should be accepted.  
For cases like the above, and in general for boundary cases
when equal times need to be handled, we compare
times in the workshop finder by adding first one
minute to the first if calling isAfter and 
subtracting one when calling isBefore. This is done 
because LocalTime's isBefore and 
isAfter methods return false for equal times. Of course,
we could have checked the isBefore and the equals too,
however this would add a lot of boilerplate code.
