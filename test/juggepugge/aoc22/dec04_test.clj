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

    (testing "It should determin if one section is contained in another"
      (is (= [false false false true true false]
             (d4/fully-contained f))))))

(deftest test-part1-and-two
  (testing "It should score part1"
    (let [lines (slurp-test-data "resources/04_test.txt")]
      (is (= 2 (d4/part1 lines))))
    (let [lines (slurp-test-data "resources/04_part1.txt")]
      (is (= 459 (d4/part1 lines)))))

  #_(testing "It should score part2"
      (let [lines (slurp-test-data "resources/04_test.txt")]
        (is (= 70 (d4/part2 lines))))
      (let [lines (slurp-test-data "resources/04_part1.txt")]
        (is (= 2545 (d4/part2 lines))))))

