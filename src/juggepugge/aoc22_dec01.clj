(ns juggepugge.aoc22-dec01
  (:require
   [clojure.string :as str]))




(defn find-most-cals [data]
  (let [find-most (fn [xs acc most]
                    (if (empty? xs)
                      (max acc most)
                      (let [x (first xs)]
                        (if (= 0 x)
                          (recur (rest xs) 0 (max acc most))
                          (recur (rest xs) (+ x acc) most)))))]
   (find-most data 0 0))) 

(defn max3 [xs m]
  (sort 
    (if (> 3 (count xs))
     (conj xs m)
     (if (< (first xs) m)
       (conj (rest xs) m)
       xs))))


(defn sum-top3 [data]
  (let [find-top3 (fn [xs acc top3]
                    (if (empty? xs)
                      (max3 top3 acc)
                      (let [x (first xs)]
                        (if (= 0 x)
                          (recur (rest xs) 0 (max3 top3 acc))
                          (recur (rest xs) (+ x acc) top3)))))]
    (apply + (find-top3 data 0 []))))
