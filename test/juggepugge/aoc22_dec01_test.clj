(ns juggepugge.aoc22-dec01-test
  (:require
    [clojure.string :as str]
    [clojure.test :refer :all]
    [juggepugge.aoc22-dec01 :refer :all]))


(defn- slurp-test-data [file]
  (let [f (slurp file)]
    (as-> f $
        (str/split $ #"\n")
        (map #(if (empty? %) 
               0
               (Integer/parseInt %))
             $))))


(deftest a-test
  (testing "slurping file"
    (let [f (slurp-test-data "resources/01_test.txt")]
      (is (= 14 (count f))))))


(deftest it-should-count-elfs
  (testing "It should calculate sum calories for top Elf"
    (let [data (slurp-test-data "resources/01_test.txt")]
      (is (= 24000 (find-most-cals data))))
    (let [data (slurp-test-data "resources/01_part1.txt")]
      (is (= 71506 (find-most-cals data)))))

  (testing "It should calculate sum calories for top three Elfs"
    (let [data (slurp-test-data "resources/01_test.txt")]
      (is (= 45000 (sum-top3 data))))
    (let [data (slurp-test-data "resources/01_part1.txt")]
      (is (= 209603 (sum-top3 data))))))


(deftest it-should-keep-three-largest
  (testing "Keep three largest"
    (is (= [1 2 4] (max3 [1 1 2] 4)))
    (is (= [4 6 8] (max3 [4 6 8] 4)))
    (is (= [4 4 4] (max3 [4 4 4] 4)))
    (is (= [5 5 6] (max3 [5 5 6] 4)))
    (is (= [4] (max3 [] 4)))
    (is (= [2 5] (max3 [2] 5)))
    (is (= [1 2 5] (max3 [2 5] 1)))))

