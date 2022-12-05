(ns repl
  (:require
    [clojure.string :as str]
    [clojure.set :as set]))



(def shape-idx
 (set/map-invert
   (into {} (map-indexed
              vector
              [:rock :paper :scissors])))) 


(let [s {:opponent :rock :shape :scissors}
      shapes [:rock :paper :scissors]
      v1 (zipmap shapes (map-indexed (fn [idx _] idx) shapes))
      v2 (set/map-invert (into {} (map-indexed vector shapes)))
      v3 (set/map-invert (zipmap (iterate inc 0) shapes))
      diff (->> ((juxt :opponent :shape) s)
                (map shape-idx)
                (reduce -))]     
 (if (= diff 0)
   :draw
   (if (< diff 0)
     :lost
     :won)))
   

; 0 => draw
; 1 => win
; -1 => lose
; 2 => win
; -2 => lose
