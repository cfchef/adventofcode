(set-env! :resource-paths #{"src"})

(deftask solve []
  (repl :init-ns 'problem-one))