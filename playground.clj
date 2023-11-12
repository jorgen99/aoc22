(ns repl
  (:require
   [clojure.string :as str]
   [clojure.set :as set]))

(def lines ["be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe"
            "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc"
            "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg"
            "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb"
            "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea"
            "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb"
            "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe"
            "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef"
            "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb"
            "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"])

(let [wires ["be" "cgeb"]] ; "edb"]]
  (reduce (fn [acc xs]
            (add-to-segments xs acc))
          {}
          wires))

(def segments
  {:1 [:3 :6]
   :2 [:1 :3 :4 :5 :7] 
   :3 [:1 :3 :4 :6 :7]
   :4 [:2 :3 :4 :6]
   :5 [:1 :2 :4 :6 :7]
   :6 [:1 :2 :4 :5 :6 :7]
   :7 [:1 :3 :6]
   :8 [:1 :2 :3 :4 :5 :6 :7]})

   
(def seg
  {
   :2 (:1 segments)
   :3 (:7 segments)
   :4 (:4 segments)
   :7 (:8 segments)})


(defn add-to-segments [wires known]
  (let [digit (-> wires count str keyword)]
    (into known (map vector (digit seg) wires))))


(defn count->digit [c]
  (let [d {2 1
           3 7
           4 4
           5 [2 3 5] 
           6 [6 9]
           7 8}]
    (get d c)))

(defn apa [wires]
  (let [k (-> wires count keyword)]
    (k seg)))
             

(->>
  (slurp "resources/2021_08_part1.txt")
  list
  (map #(str/split % #"\n"))
  first
  (map #(str/split % #"\|"))
  (map #(map str/trim %))
  (map last)
  (map (fn [s] (str/split s #" ")))
  (flatten)
  (map str/join)
  (filter #(#{2 3 4 7} (count %))))
  ;count)

(as->
  "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf" l)
  
