(ns repl
  (:require
    [clojure.string :as str]))


(let [a {:apa 10}]
  a)


(let [f (slurp "resources/01_test.txt")
      parsed (as-> f $
                 (str/split $ #"\n")
                 (map #(if (empty? %)
                        0
                        (Integer/parseInt %))
                      $))
      trav (fn [xs acc most]
             (if (empty? xs)
               (max acc most)
               (let [x (first xs)]
                 (if (= 0 x)
                   (recur (rest xs) 0 (max acc most))
                   (recur (rest xs) (+ x acc) most)))))

      xs [1 2 4 5]
      n (if (< (first xs) 4)
          (sort (conj (rest xs) 4))
          xs)]


  n)
(some [1 3] [1 4])

(let [shapes {:opponent :rock :shape :paper}]
  (assoc shapes :outcome :won))

  ;(trav parsed 0 0))

