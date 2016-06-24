(defproject de.otto/tesla-vault "0.1.4"
  :description "Read vault secrets in your edn config"
  :url "https://github.com/otto-de/tesla-vault"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url  "https://github.com/otto-de/tesla-vault"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.1.0"]
                 [environ "1.0.3"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.logging "0.3.1"]])
