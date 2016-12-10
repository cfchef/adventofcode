(set-env! :source-paths #{"src"}
          :resource-paths #{"src"})

(deftask run-one []
  (repl :init-ns 'problem-one))

(deftask run-two []
  (repl :init-ns 'problem-two))