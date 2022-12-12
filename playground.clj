(ns repl
  (:require
    [clojure.string :as str]
    [clojure.set :as set]))



(let [lines (slurp-test-data "resources/05_test.txt")
      prio (fn [ch]
             (if (< (int ch) (int \a))
               (+ 26 (- (int ch) 64))
               (- (int ch) 96)))])
(empty? (rest '(\a \b)))
