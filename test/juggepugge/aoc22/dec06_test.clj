(ns juggepugge.aoc22.dec06-test
  (:require
    [clojure.string :as str]
    [clojure.test :refer [deftest testing is]]
    [juggepugge.aoc22.dec06 :as d6]))


(defn- slurp-test-data [file]
  (str/split (slurp file) #"\n"))


(deftest a-test
  (testing "slurping file"
    (let [f (slurp-test-data "resources/06_test.txt")]
      (is (= 10 (count f))))))


(deftest dec-06-tests
  (let [lines (slurp-test-data "resources/06_test.txt")]
    (testing "It should find the first marker"
      (is (= 5 (d6/find-marker (first lines)))))

    (testing "It should find the first marker"
      (is (= [5 6 10 11] (map d6/find-marker (take 4 lines)))))

    (testing "It should find the first message"
      (is (= [19 23 23 29 26 ] (map d6/find-message (take-last 5 lines)))))))

(deftest test-part1-and-two
  (testing "It should score part1"
    (let [lines (slurp-test-data "resources/06_test.txt")]
      (is (= 5 (d6/part1 lines))))
    (let [lines (slurp-test-data "resources/06_part1.txt")]
      (is (= 1356 (d6/part1 lines)))))

  (testing "It should score part2"
    (let [lines (slurp-test-data "resources/06_test.txt")]
      (is (= 19 (d6/part2 (take-last 5 lines)))))
    (let [lines (slurp-test-data "resources/06_part1.txt")]
      (is (= 2564 (d6/part2 lines))))))

