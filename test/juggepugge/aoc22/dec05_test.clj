(ns juggepugge.aoc22.dec05-test
  (:require
    [clojure.string :as str]
    [clojure.test :refer [deftest testing is]]
    [juggepugge.aoc22.dec05 :as d5]))


(defn- slurp-test-data [file]
  (str/split (slurp file) #"\n"))


(deftest a-test
  #_(testing "fail to print prns"
      (let [lines (slurp-test-data "resources/05_test.txt")
            s (d5/->stacks lines)]
        (is (= true false))))

  (testing "slurping file"
    (let [lines (slurp-test-data "resources/05_test.txt")]
      (is (= 9 (count lines))))))


(deftest dec-05-tests
  (testing "It should put letters into the correct stacks"
    (let [lines (slurp-test-data "resources/05_test.txt")
          expected {1 [\Z \N] 2 [\M \C \D] 3 [\P]}]
      (is (= expected (d5/->stacks lines)))))

  (testing "It should know the number of instructions"
    (let [lines (slurp-test-data "resources/05_test.txt")
          instructions (d5/instructions lines)
          parsed (d5/parse instructions)]
      (is (= 4 (count instructions)))
      (is (= '(1 2 1) (first parsed)))
      (is (= '(1 1 2) (last parsed)))))

  (testing "It should execute one row of instructions"
    (let [stacks {1 [\Z \N] 2 [\M \C \D] 3 [\P]}
          expected {1 [\Z \N \D] 2 [\M \C] 3 [\P]}
          instructions (first (d5/parse  '("move 1 from 2 to 1")))]
      (is (= expected (d5/move-one stacks instructions)))))

  (testing "It should execute one more row of instructions"
    (let [stacks {1 [\M \C], 2 [], 3 [\P \Z \N \D]}
          expected {1 [], 2 [\M \C], 3 [\P \Z \N \D]}
          one-instruction (first (d5/parse '("move 2 from 1 to 2")))]
      (is (= expected (d5/move-one stacks one-instruction)))))

  (testing "It should execute all moves"
    (let [lines (slurp-test-data "resources/05_test.txt")
          expected {1 [\M] 2 [\C] 3 [\P \Z \N \D]}]
      (is (= expected (d5/move-all lines))))))


(deftest test-part1-and-two
  #_(testing "It should score part1"
      (let [lines (slurp-test-data "resources/05_test.txt")
            stacks (d5/part1 lines)]
        (is (= "CMZ" stacks)))
      (let [lines (slurp-test-data "resources/05_part1.txt")]
        (is (= "HBTMTBSDC" (d5/part1 lines)))))

  (testing "It should score part2"
    (let [lines (slurp-test-data "resources/05_test.txt")
          stacks (d5/part2 lines)]
      (is (= "MCD" stacks)))
    (let [lines (slurp-test-data "resources/05_part1.txt")]
      (is (= "PQTJRSHWS" (d5/part2 lines))))))

