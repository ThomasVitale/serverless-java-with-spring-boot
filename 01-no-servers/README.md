# No Servers

## Prerequisites

Ensure you have the following tools installed in your local environment:

* Kubernetes `kubectl`
* Carvel `kctrl`
* Carvel `kapp`.

Then, create a local Kubernetes cluster with `kind`.

```shell script
cat <<EOF | kind create cluster --config=-
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
name: kadras
nodes:
- role: control-plane
  extraPortMappings:
  - containerPort: 80
    hostPort: 80
    protocol: TCP
  - containerPort: 443
    hostPort: 443
    protocol: TCP
EOF
```

## Deploy Carvel kapp-controller

The platform relies on the Kubernetes-native package management capabilities offered by Carvel kapp-controller. You can install it with Carvel `kapp` (recommended choice) or `kubectl`.

```shell script
kapp deploy -a kapp-controller -y \
  -f https://github.com/carvel-dev/kapp-controller/releases/latest/download/release.yml
```

## Add the Kadras Repository

Add the Kadras repository to make the platform packages available to the cluster.

```shell script
kctrl package repository add -r kadras-packages \
  --url ghcr.io/kadras-io/kadras-packages \
  -n kadras-packages --create-namespace
```

## Configure the Platform

The installation of the Kadras platform can be configured via YAML. Create a `values.yml` file with any configuration you need for the platform. The following is a minimal configuration example for a local installation.

```yaml
platform:
  profile: serving
  infrastructure_provider: local
  ingress:
    domain: 127.0.0.1.sslip.io
```

## Install the Platform

Reference the `values.yml` file you created in the previous step and install the Kadras Engineering Platform.

```shell script
kctrl package install -i engineering-platform \
  -p engineering-platform.packages.kadras.io \
  -v 0.10.0 \
  -n kadras-packages \
  --values-file values.yml
```

## Verify the Installation

Verify that all the platform components have been installed and properly reconciled.

```shell script
kctrl package installed list -n kadras-packages
```
