(ns user 
  (:require [portal.api :as p]
            [com.brunobonacci.mulog :as u]))

(def p (p/open {:launcher :vs-code}))
(add-tap #'p/submit)
(u/start-publisher! {:type :console
                     :pretty? true})