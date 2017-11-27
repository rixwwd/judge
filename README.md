# What is this?

This is script engine for decide to 3-D Secure transaction status.
It's use to decide transaction status in ARes Message by ACS. 


# Syntax

~~~
rule := action "quick"? expression
action := "reject"  | "challenge" | "authenticated" | "cancel" | "unavailable" | "attempt"
expression := expression0 ("or" expression0)?
expression0 := expression1 ("and" expression1)?
expression1 := "not"? expression2
expression2 := condition | "(" expression ")"
condition := fieldname operator value
fielfname := [a-zA-Z][a-zA-Z0-9]*
operator := "eq" | "ne" | "le" | "lt" | "ge" | "gt"
value := "\"" [a-zA-Z0-9] "\""
~~~
