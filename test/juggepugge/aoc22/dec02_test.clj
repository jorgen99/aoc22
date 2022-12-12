(ns juggepugge.aoc22.dec02-test
  (:require
    [clojure.string :as str]
    [clojure.test :refer [deftest testing is]]
    [juggepugge.aoc22.dec02 :as d2]))


(defn- slurp-test-data [file]
  (str/split (slurp file) #"\n"))


(deftest a-test
  (testing "slurping file"
    (let [f (slurp-test-data "resources/02_test.txt")]
      (is (= 3 (count f))))))


(deftest dec-02-tests
  (testing "It should split a line into parts"
    (is (= ["A" "Y"] (d2/line->parts "A Y")))
    (is (= ["Q" "P"] (d2/line->parts "Q P"))))

  (testing "It should calculate the score of a round"
    (let [round {:shape :paper :outcome :won}]
      (is (= 2 (d2/score-shape round)))
      (is (= 6 (d2/score-outcome round)))
      (is (= 8 (d2/score-round round)))
      (is (= 3 (d2/score-round {:shape :scissors :outcome :lost})))
      (is (= 1 (d2/score-round {:shape :rock :outcome :lost})))
      (is (= 4 (d2/score-round {:shape :rock :outcome :draw})))))

  (testing "It should parse a line"
    (is (= {:opponent :rock :shape :paper} (d2/line->shapes ["A" "Y"])))
    (is (= {:opponent :paper :shape :scissors} (d2/line->shapes ["B" "Z"])))
    (is (= {:opponent :scissors :shape :rock} (d2/line->shapes ["C" "X"]))))

  (testing "It should determine the outcome"
    (is (= :won (d2/win-lose? (d2/line->shapes ["A" "Y"]))))
    (is (= :draw (d2/win-lose? (d2/line->shapes ["B" "Y"]))))
    (is (= :lost (d2/win-lose? (d2/line->shapes ["C" "Y"])))))

  (testing "It should parse a line"
    (let [shape (d2/line->shapes ["A" "Y"])
          outcome (d2/shapes->outcome shape)]
      (is (= {:opponent :rock :shape :paper :outcome :won} outcome))))


  (testing "It should calculate the score for one line"
    (is (= 8 (d2/parse-and-score "A Y")))))


(deftest test-part1-and-two
  (testing "It should score part1"
    (let [lines (slurp-test-data "resources/02_test.txt")]
      (is (= 15 (d2/part1 lines))))
    (let [lines (slurp-test-data "resources/02_part1.txt")]
      (is (= 11767 (d2/part1 lines)))))

  (testing "It should score part2"
    (let [lines (slurp-test-data "resources/02_test.txt")]
      (is (= 12 (d2/part2 lines))))
    (let [lines (slurp-test-data "resources/02_part1.txt")]
      (is (= 13886 (d2/part2 lines))))))

