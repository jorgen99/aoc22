(ns juggepugge.aoc22.dec05
  (:require
    [clojure.string :as str]))


(defn ->stack-lines [lines]
  (take-while #(not (empty? %)) lines))
  

(defn- add-letter-to-column [acc idx letter]
  (let [stack-no (inc idx)]
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
  ;; butlast -> remove stack no line
  ;; map rest -> remove first char in each line
  ;; map butlast -> remove last char in each line
  ;; then take every 4th char to get the container letters (or \space)
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
        fromv (get stacks from)
        from-idx (- (count fromv) no)
        being-moved (subvec fromv from-idx)
        fromv-after (subvec fromv 0 from-idx)
        tov (get stacks to)
        tov-after (apply conj tov being-moved)]
    (-> stacks
        (assoc from fromv-after)
        (assoc to tov-after))))


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


(defn part2 [lines]
 (part1 lines))

