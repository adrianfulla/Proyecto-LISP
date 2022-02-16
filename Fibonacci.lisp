;Función de fibonacci.
(
    defun fibonacci(num)
        (cond 
            ( 
                (<= num 2)
                    1
            )
            (t 
                (+
                    (fibonacci (- num 1))
                    (fibonacci (- num 2)) 
                )
            )            
        )
)
(print (fibonacci 5))