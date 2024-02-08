(ns antoine.system
(:require [aero.core :refer [read-config]]
          [clojure.java.io :as io]))

(def configuracion (read-config (io/resource "config.edn")))

