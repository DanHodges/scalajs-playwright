credentials += Credentials(
  "Artifactory Realm",
  "artifactory.prod.lifeway.com",
  "diaspora-build",
  sys.env.getOrElse("BUILD_ENC_KEY", "")
)