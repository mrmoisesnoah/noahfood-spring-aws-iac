package com.myorg;

import software.amazon.awscdk.App;

public class NoahAwsInfraApp {
    public static void main(final String[] args) {
        App app = new App();

        NoahVpcStack vpcStack = new NoahVpcStack(app, "Vpc");
        NoahClusterStack clusterStack = new NoahClusterStack(app, "Cluster", vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        NoahRdsStack rdsStack = new NoahRdsStack(app, "Rds", vpcStack.getVpc());
        rdsStack.addDependency(vpcStack);

        NoahServiceStack noahServiceStack = new NoahServiceStack(app, "Service", clusterStack.getCluster());
        noahServiceStack.addDependency(clusterStack);
        noahServiceStack.addDependency(rdsStack);
        app.synth();
    }
}

