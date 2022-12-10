(ns juggepugge.aoc22.dec05
  (:require
    [clojure.string :as str]))


(defn ->stack-lines [lines]
  (take-while #(not (empty? %)) lines))
  

(defn no-of-stacks [lines]
  (let [number-line (last (->stack-lines lines))]
    (count
      (remove empty?
        (str/split number-line #" ")))))


(defn- add-letter-to-column [acc idx letter]
  (let [stack-no (keyword (str (inc idx)))]
    (update-in acc [stack-no]
      (fn [v]
        (vec (cons letter (vec v)))))))


(defn- add-letter-if-not-a-space [col-acc [idx letter]] 
  (if (= \space letter)
    col-acc
    (add-letter-to-column col-acc idx letter)))


(defn instructions [lines]
  (filter #(str/starts-with? % "move") lines)) 


(defn parse [instructions]
  (map
    #(map read-string (re-seq #"\d+" %))
    instructions)) 
 


(defn ->stacks [lines]
  (let [stacks (map butlast (map rest (butlast (->stack-lines lines))))
        stack-letters (mapv #(take-nth 4 %) stacks)]
    (reduce (fn [acc row]
              (reduce
                add-letter-if-not-a-space
                acc
                (map-indexed vector row)))
            {}
            stack-letters)))

(defn move-one [stacks instruction]
  (let [[no from to] instruction
        from-key (keyword (str from))
        to-key (keyword (str to))
        fromv (get stacks from-key)
        being-moved (reverse (subvec fromv (- (count fromv) no)))
        fromv-after (subvec fromv 0 (- (count fromv) no))
        tov (get stacks to-key)
        tov-after (apply conj tov being-moved)]
    (-> stacks
        (assoc from-key fromv-after)
        (assoc to-key tov-after))))


(defn move-all [lines]
  (let [stacks (->stacks lines)
        parsed-instructions (parse (instructions lines))]
    (reduce (fn [acc one-move-instruction]
              (move-one acc one-move-instruction))
      stacks
      parsed-instructions)))
      

(defn part1 [lines]
  (str/join "" (map last
                    (map last
                         (seq (into (sorted-map) (move-all lines)))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


#_(defn part2 [lines]
   ( (preduce + (map parse-and-score2 lines))))

