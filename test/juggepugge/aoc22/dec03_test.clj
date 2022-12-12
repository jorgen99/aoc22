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
  (let [f (slurp-test-data "resources/03_test.txt")]

    (testing "It should split a line into parts"
      (is (= ["abc" "ABC"] (d3/line-in-half "abcABC")))
      (is (= ["12345678" "abcdefgh"] (d3/line-in-half "12345678abcdefgh"))))

    (testing "It should find common letters for a split line"
      (is (= \p (d3/common-letter
                  (d3/line-in-half "vJrwpWtwJgWrhcsFMMfFFhFp")))))

    (testing "It should calculate priority for one line"
      (is (= 16  (d3/prio-for-line "vJrwpWtwJgWrhcsFMMfFFhFp"))))

    (testing "It should get the priority of a letter"
      (is (= 1 (d3/prio \a)))
      (is (= 26 (d3/prio \z)))
      (is (= 27 (d3/prio \A)))
      (is (= 52 (d3/prio \Z)))
      (is (= 22 (d3/prio \v)))
      (is (= 38 (d3/prio \L))))

    (testing "It should partition lines by 3"
      (is (= 2 (count (partition 3 f)))))

    (testing "It should calculate the prio for the first three lines"
      (is (= 18 (d3/prio-for-lines (first (partition 3 f))))))))


(deftest test-part1-and-two
  (testing "It should score part1"
    (let [lines (slurp-test-data "resources/03_test.txt")]
      (is (= 157 (d3/part1 lines))))
    (let [lines (slurp-test-data "resources/03_part1.txt")]
      (is (= 7997 (d3/part1 lines)))))

  (testing "It should score part2"
    (let [lines (slurp-test-data "resources/03_test.txt")]
      (is (= 70 (d3/part2 lines))))
    (let [lines (slurp-test-data "resources/03_part1.txt")]
      (is (= 2545 (d3/part2 lines))))))

