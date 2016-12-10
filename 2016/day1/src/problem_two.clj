(ns problem-two
  (:require [clojure.string :as str]))

(def input (str/split (slurp "src/input.txt") #",\s+")) ; Read in and parse input
(def position (atom [1 1]))                             ; Starting position
(def direction (atom "N"))                              ; Starting direction
(def points (atom []))                                  ; Collection of positions
(def target (atom []))                                  ; Target position to HQ

(defn move [blocks]
  (dotimes [n blocks]
    (case @direction
      "N" (reset! position [(+ (@position 0) 1) (@position 1)])
      "E" (reset! position [(@position 0) (+ (@position 1) 1)])
      "S" (reset! position [(- (@position 0) 1) (@position 1)])
      "W" (reset! position [(@position 0) (- (@position 1) 1)]))
    (swap! points conj @position)
    (let [point (filterv #(= (% 1) 2) (frequencies @points))]
      (when (= (get-in point [0 1]) 2)
        (when (zero? (count @target))
          (println (str "Target: " @position))
          (reset! target (get-in point [0 0])))))))

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
  (Math/abs (apply + (map dec @target))))

(defn main []
  (dorun
    (map #(comp
      (turn ((str/split % #"\d+") 0))
      (move (Integer/parseInt ((str/split % #"\D+") 1)))) input))
  (println (str "Distance to HQ: " (distance))))