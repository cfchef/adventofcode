(ns problem-one
  (:require [clojure.string :as str]))

(def input (str/split (slurp "input.txt") #",\s+")) ; Read in and parse input
(def position (atom [1 1]))                         ; Starting position
(def direction (atom "N"))                          ; Starting direction

(defn move [blocks]
  (case @direction
    "N" (reset! position [(+ (@position 0) blocks) (@position 1)])
    "E" (reset! position [(@position 0) (+ (@position 1) blocks)])
    "S" (reset! position [(- (@position 0) blocks) (@position 1)])
    "W" (reset! position [(@position 0) (- (@position 1) blocks)])))

(defn turn [point]
  (case point
    "R" (case @direction
      "N" (reset! direction "E")
      "E" (reset! direction "S")
      "S" (reset! direction "W")
      "W" (reset! direction "N"))
    "L" (case @direction
      "N" (reset! direction "W")
      "E" (reset! direction "N")
      "S" (reset! direction "E")
      "W" (reset! direction "S"))))

(defn distance []
  (Math/abs (apply + (map dec @position))))

(defn main []
  (dorun
    (map #(comp
      (println "Working on:" %)
      (println "Current Direction:" @direction)
      (println "Current Position:" @position)
      (turn ((str/split % #"\d+") 0))
      (println "New Direction:" @direction)
      (move (Integer/parseInt ((str/split % #"\D+") 1)))
      (println "New Position:" @position)) input))
  (println (str "Distance to HQ: " (distance))))