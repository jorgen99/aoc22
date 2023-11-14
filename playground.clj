(ns repl
  (:require
   [clojure.string :as str]
   [clojure.set :as set]))


;; Part 1
(->>
  (slurp "resources/2021_08.txt")  ;; read file
  (#(str/split % #"\n"))           ;; split on new-line
  (map #(str/split % #"\|"))       ;; split each line on |
  (map #(map str/trim %))          ;; trim whitespace
  (map last)                       ;; only use output values
  (map #(str/split % #" "))        ;; split output values
  flatten                          ;; one list of all output values
  (filter #(#{2 3 4 7} (count %))) ;; only keep vals with length 2, 3, 4 & 7
  count)                           ;; how many did we get?


;; Part 2

;; Calculate the output value for one row
;; Ex.
;;   acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf
;; ->
;;   5353
(defn output-value [row]
  (let [patterns-and-values (->> (str/split row #"\|")
                                 (map str/trim)
                                 (map #(str/split % #" ")))
        patterns (first patterns-and-values)
        values (last patterns-and-values)
        length->signal-pattern (into {} (map vector (map count patterns) patterns))
        signal->segment (two_five_six patterns)
        signal->segment (add-last-four-signals length->signal-pattern signal->segment)]

    (->> values                                            ;; the four output ("cdfeb" "fcadb" "cdfeb" "cdbaf")
         (map #(calculate-single-digit % signal->segment)) ;; list of digits as strings ("5" "3" "5" "3")
         str/join                                          ;; "5353"
         Integer/parseInt)))                               ;; as an int


;; Calculte the output of a single value
;; Ex.
;;  cdfeb -> "5"
(defn calculate-single-digit [value signal->segment]
  (->> value
       (map str)
       (map signal->segment)
       sort
       segments
       str))

;; Given that we know the signals for segments 5, 2 and 6
;; we can get:
;;    segment 3 from signal-pattern of length 2 (the digit 1)
;; this gives us:
;;    segment 1 from signal-pattern of length 3 (the digit 7)
;; which then gives us:
;;    segment 4 from signal-pattern of length 4 (the digit 4)
;; finally
;;    segment 7 from the letter thats left
(defn add-last-four-signals [length->signal-pattern signal->segment]
  (let [length-segment [[2 3] [3 1] [4 4]]
        pattern-segment (mapv (fn [[length segment]]
                                [(get length->signal-pattern length)
                                 segment])
                              length-segment)
        pattern-segment (conj pattern-segment ["abcdefg" 7])]
    (three-one-four-seven pattern-segment signal->segment)))


;; Given a list of pattern-segment tuples we can
;; reduce the segments for signals 3, 1, 4 and 7
;;
;; Ex.
;;  [["ab" 3] ["dab" 1] ["eafb" 4] ["abcdefg" 7]]
;;  {"g" 5, "e" 2, "b" 6}
;; ->
;;   {"g" 5, "e" 2, "b" 6, "a" 3, "d" 1, "f" 4, "c" 7}
(defn three-one-four-seven [pattern-segment signal->segment]
  (reduce (fn [acc tuple]
            (let [signal-pattern (first tuple)
                  segment (last tuple)
                  missing-signal (deduce-missig-signal signal-pattern acc)]
              (assoc acc missing-signal segment)))
          signal->segment
          pattern-segment))


;; Given that we have the signal to segment map with the
;; known signals, if we have a signal pattern where only
;; one of the signals is NOT present in the map, we can
;; deduce the missing one signal
;;
;; Ex. signal pattern "ab" and signal->segment {"g" 5, "e" 2, "b" 6}
;; -> "a"
(defn deduce-missig-signal [signal-pattern signal->segment]
  (as-> signal-pattern $
       (map str $)                                ;; into list of letters
       (into #{} $)                               ;; convert to a set
       (remove (select-keys signal->segment $) $) ;; remove letters we already know
       (first $)))                                ;; return the only letter left


;; If we count the number of times each segment
;; appears in 0-9 we notice that three of them
;; have a distict frequency:
;; Segement 5 -> 4 times
;; Segement 2 -> 6 times
;; Segement 6 -> 9 times
;; Given all ten signal patterns we can figure out
;; which signal belongs to these segments by calculating
;; the frequency of the signals
(defn two_five_six [signal-patterns]
  (->> signal-patterns
       letter->freq                         ;; frequency of letters as a map {"a" 8, "c" 7...
       set/map-invert                       ;; make frequency key and letter value
       (#(select-keys % [4 6 9]))           ;; seg5 occurs 4times, seg2 6times, seg6 9 times
       (#(set/rename-keys % {4 5 6 2 9 6})) ;; replace freq with segment no
       set/map-invert))                     ;; invert back to letter as key and segment as value
                                            ;;   i.e -> {"g" 5, "e" 2, "b" 6}


;; Calculate the frequency of all signals given the list
;; of signal patterns for digits 0-9
;;
;; With the signal patterns from the part two example:
;;   acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab
;; ->
;;   {"a" 8, "c" 7, "e" 6, "d" 8, "g" 4, "f" 7, "b" 9}
(defn letter->freq [signal-patterns]
  (reduce (fn [acc signal-pattern]
            (->> signal-pattern
                 (map str)               ;; -> map of letters
                 frequencies             ;; frequency of letters in one signal-pattern
                 (merge-with + acc)))    ;; add frequencies to the total for each letter
          {}
          signal-patterns))


;; Maps list of segments to digit
(def segments
  {[1 2 3 5 6 7] 0
   [3 6] 1
   [1 3 4 5 7] 2
   [1 3 4 6 7] 3
   [2 3 4 6] 4
   [1 2 4 6 7] 5
   [1 2 4 5 6 7] 6
   [1 3 6] 7
   [1 2 3 4 5 6 7] 8
   [1 2 3 4 6 7] 9})


(->> (slurp "resources/2021_08.txt")  ;; read file
     (#(str/split % #"\n"))           ;; split on new-line
     (map output-value)               ;; calculate value for each output
     (reduce +))                      ;; sum

