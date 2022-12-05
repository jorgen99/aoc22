(ns juggepugge.aoc22.dec03-test
  (:require
    [clojure.string :as str]
    [clojure.test :refer [deftest testing is]]
    [juggepugge.aoc22.dec03 :as d3]))


(defn- slurp-test-data [file]
  (str/split (slurp file) #"\n"))


(deftest a-test
  (testing "slurping file"
    (let [f (slurp-test-data "resources/03_test.txt")]
      (is (= 6 (count f))))))


(deftest dec-03-tests
  (testing "It should split a line into parts"
    (is (= ["abc" "ABC"] (d3/line-in-half "abcABC")))
    (is (= ["12345678" "abcdefgh"] (d3/line-in-half "12345678abcdefgh")))))


#_(deftest test-part1-and-two
    (testing "It should score part1"
      (let [lines (slurp-test-data "resources/03_test.txt")]
        (is (= 15 (d3/part1 lines))))
      (let [lines (slurp-test-data "resources/03_part1.txt")]
        (is (= 11767 (d3/part1 lines)))))

    #_(testing "It should score part2"
       (let [lines (slurp-test-data "resources/03_test.txt")]
         (is (= 12 (d3/part2 lines))))
       (let [lines (slurp-test-data "resources/03_part1.txt")]
         (is (= 13886 (d3/part2 lines))))))

