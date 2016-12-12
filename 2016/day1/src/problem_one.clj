(ns problem-one
  (:require [clojure.string :as str]))

(def input (str/split (slurp "src/input.txt") #",\s+"))

(defn move [dir pos blocks]
  (case dir
    "N" [(+ (pos 0) blocks) (pos 1)]
    "E" [(pos 0) (+ (pos 1) blocks)]
    "S" [(- (pos 0) blocks) (pos 1)]
    "W" [(pos 0) (- (pos 1) blocks)]))

(defn turn [dir point]
  (case point
    "R" (case dir
      "N" "E"
      "E" "S"
      "S" "W"
      "W" "N")
    "L" (case dir
      "N" "W"
      "E" "N"
      "S" "E"
      "W" "S")))

(defn distance [pos]
  (Math/abs (apply + (map dec pos))))

(defn main []
  (let [position (atom [1 1])
        direction (atom "N")]
    (dorun
      (map #(comp
        (println "Working on:" %)
        (println "Current Direction:" @direction)
        (println "Current Position:" @position)
        (reset! direction (turn @direction (subs % 0 1)))
        (println "New Direction:" @direction)
        (reset! position
          (move @direction @position (Integer/parseInt (subs % 1))))
        (println "New Position:" @position)) input))
    (println (str "Distance to HQ: " (distance @position)))))