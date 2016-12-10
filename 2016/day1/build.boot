(set-env! :resource-paths #{"src" "resources"})

(deftask run-one []
  (repl :init-ns 'problem-one))

(deftask run-two []
  (repl :init-ns 'problem-two))