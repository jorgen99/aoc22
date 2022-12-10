(ns repl
  (:require
    [clojure.string :as str]
    [clojure.set :as set]))



(let [lines (slurp-test-data "resources/05_test.txt")
      instructions (filter #(str/starts-with? % "move") lines)])

