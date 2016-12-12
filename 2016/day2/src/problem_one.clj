(ns problem-one
  (:require [clojure.string :as str]))

(def input (str/split (slurp "src/input.txt") #"\r\n")) ; Read in and parse input
(def position (atom [1 1]))                             ; Starting position
(def keys (atom []))                                    ; Buttons pressed

(defn main []
  (dotimes [n (count input)]
    (swap! )))

(defn move [pos dir]
  (and (= dir "R") (< (pos 0) 2) (inc (pos 0)))
  (and (= dir "L") (> (pos 0) 0) (dec (pos 0)))
  (and (= dir "U") (< (pos 1) 2) (inc (pos 1)))
  (and (= dir "D") (> (pos 1) 0) (dec (pos 1))))

; Key pad:
; ---------
; | 1 2 3 |
; | 4 5 6 |
; | 7 8 9 |
; ---------

(defn button [pos]
  (let [keys {[0 2] 1 [1 2] 2 [2 2] 3 [0 1] 4 [1 1] 5 [2 1] 6 [0 0] 7 [1 0] 8 [2 0] 9}]
    (get (first (filter #(= (% 0) pos) keys)) 1)))