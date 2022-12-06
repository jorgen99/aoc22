(ns juggepugge.aoc22.dec01)


(defn- find-max [xs acc current-max max-fn]
  (let [finder (fn [xs acc current-max]
                 (if (empty? xs)
                   (max-fn acc current-max)
                   (let [x (first xs)]
                     (if (= "" x)
                       (recur (rest xs) 0 (max-fn acc current-max))
                       (recur (rest xs) (+ (Integer/parseInt x) acc) current-max)))))]
    (finder xs acc current-max)))


(defn part1 [data]
  (find-max data 0 0 max)) 


(defn max3 [m xs]
  (sort 
    (if (> 3 (count xs))
     (conj xs m)
     (if (< (first xs) m)
       (conj (rest xs) m)
       xs))))


(defn part2 [data]
  (apply + (find-max data 0 [] max3)))

