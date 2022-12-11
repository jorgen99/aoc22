(ns repl
  (:require
    [clojure.string :as str]
    [clojure.set :as set]))



(let [lines (slurp-test-data "resources/05_test.txt")
      instructions (filter #(str/starts-with? % "move") lines)
      stacks {1 [\Z \N] 2 [\M \C \D] 3 [\P]}
      stack-no 2]
  (stack-no stacks))
