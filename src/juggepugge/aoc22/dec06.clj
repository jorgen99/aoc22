(ns juggepugge.aoc22.dec06)


(defn- all-unique [[_idx four-chars] min-unique]
  (= min-unique (count (set four-chars))))


(defn find-idx-of-unique [line min-unique]
  (let [idx (inc
              (first 
                (last
                  (take-while #(not (all-unique % min-unique))
                              (map-indexed vector (partition min-unique 1 line))))))]
    (+ idx min-unique))) 
    

(defn find-marker [line]
  (find-idx-of-unique line 4)) 



(defn find-message [line]
  (find-idx-of-unique line 14)) 
    

(defn part1 [lines]
  (find-marker (first lines)))


(defn part2 [lines]
  (find-message (first lines)))

