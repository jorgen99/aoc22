(ns juggepugge.aoc22.dec04-test
  (:require
    [clojure.string :as str]
    [clojure.test :refer [deftest testing is]]
    [juggepugge.aoc22.dec04 :as d4]))


(defn- slurp-test-data [file]
  (str/split (slurp file) #"\n"))


(deftest a-test
  (testing "slurping file"
    (let [f (slurp-test-data "resources/04_test.txt")]
      (is (= 6 (count f))))))


(deftest dec-04-tests
  (let [f (slurp-test-data "resources/04_test.txt")]

    (testing "It should parse lines into range-sets"
      (is (= [#{2 3 4} #{6 7 8}] (d4/line->range-sets "2-4,6-8"))))

    (testing "It should determine if one section is contained in another"
      (is (= [false false false true true false]
             (d4/fully-contained f))))

    (testing "It should determine if one section overlaps another"
      (is (= [false false true true true true]
             (d4/overlaps f))))))

(deftest test-part1-and-two
  (testing "It should score part1"
    (let [lines (slurp-test-data "resources/04_test.txt")]
      (is (= 2 (d4/part1 lines))))
    (let [lines (slurp-test-data "resources/04_part1.txt")]
      (is (= 459 (d4/part1 lines)))))

  (testing "It should score part2"
    (let [lines (slurp-test-data "resources/04_test.txt")]
      (is (= 4 (d4/part2 lines))))
    (let [lines (slurp-test-data "resources/04_part1.txt")]
      (is (= 779 (d4/part2 lines))))))

