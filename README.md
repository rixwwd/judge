# What is this?

This is script engine for decide to 3-D Secure transaction status.
It's use to decide transaction status in ARes Message by ACS. 


# Example

~~~
reject acctNumber eq "9999999999999999"
challenge acctNumber eq "9999999999999998"
challenge purchaseAmount ge "100000" and purchaseCurrency eq "392"
authenticated purchaseAmount lt "100000" and purchaseCurrency eq "392"
~~~

# Syntax

~~~
rule := action "quick"? expression
action := "reject"  | "challenge" | "authenticated" | "cancel" | "unavailable" | "attempt"
expression := expression0 ("or" expression0)?
expression0 := expression1 ("and" expression1)?
expression1 := "not"? expression2
expression2 := condition | "(" expression ")"
condition := fieldname operator value | boolean
fielfname := [a-zA-Z][a-zA-Z0-9]*
operator := "eq" | "ne" | "le" | "lt" | "ge" | "gt"
value := boolean | integer | string
integer := [0-9]+
boolean := "true" | "false"
string := "\"" [a-zA-Z0-9] "\""
~~~
