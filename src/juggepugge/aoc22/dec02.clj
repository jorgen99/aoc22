(ns juggepugge.aoc22.dec02
  (:require
    [clojure.string :as str]))


(def shape-scores
  {:rock 1
   :paper 2
   :scissors 3})

(def outcome-scores
  {:won 6
   :draw 3
   :lost 0})


(defn score-shape [round]
  (->> round
       :shape
       (get shape-scores)))


(defn score-outcome [round]
  (->> round
       :outcome
       (get outcome-scores))) 


(defn score-round [round]
  (+ (score-outcome round)
     (score-shape  round)))


(defn letter->shape [letter]
  (case letter
    ("A" "X") :rock
    ("B" "Y") :paper
    ("C" "Z")  :scissors))


(defn line->shapes [[opponent my-shape]]
  {:opponent (letter->shape opponent) 
   :shape (letter->shape my-shape)}) 


(defn win-lose? [{:keys [opponent shape]}]
  (if (= opponent shape)
    :draw
    (case [opponent shape] 
      ([:rock :paper]
       [:paper :scissors]
       [:scissors :rock]) :won
      :lost)))

(defn shapes->outcome [shapes]
  (assoc shapes :outcome (win-lose? shapes)))

(defn line->parts [line]
  (str/split line #" "))


(defn parse-and-score [line]
  (-> line
      line->parts
      line->shapes
      shapes->outcome
      score-round))


(defn part1 [lines]
  (reduce + (map parse-and-score lines)))

(defn part2 [lines])

