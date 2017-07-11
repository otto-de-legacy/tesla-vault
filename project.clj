(defproject de.otto/tesla-vault "1.1.1"
  :description "Read vault secrets in your edn config"
  :url "https://github.com/otto-de/tesla-vault"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url  "https://github.com/otto-de/tesla-vault"}
  :profiles {:dev {:plugins [[lein-release/lein-release "1.0.9"]]}}
  :lein-release {:deploy-via :clojars}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.6.1"]
                 [environ "1.1.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.logging "0.4.0"]])
