(
    defun hi (name)
        (print (concatenate 'string "Hola mundo " name))
)

(
    defun sum(a b) 
        (+ a b)
)

(
    defun sumList(l)
        (print (reduce '+ l)) 
)
;(hi "Diego")
;(print (sum 1 2))
(sumList '(4 5 8 7 6 10))