# Setup Playwright in CICD

# Running in Pipeline in Github Actions

One of the most powerful ways to run Playwright tests is as part of your CICD (Continuous Integration Continuous Delivery) process. Get feedback running your tests after the code is integrated but before it ships allows you to guarantee a certain degree of quality of your product.
There can be different ways of doing this. With or without mocking. In this exercise we will see how to run the tests in the pipeline. 

The picture below shows a typical setup of a CICD pipeline setup.
 ![Exercise1specfile](./images/cicd.png "CICD overview")

## Exercise: Setup and create Playwright tests in Github Actions

https://docs.github.com/en/actions/using-workflows/workflow-syntax-for-github-actions

Typically the Github action would already exist, since this is the place where the dev team creates a pipeline to build and ship their code. In this exercise we will create this ourselves in order gain knowledge so that you are able to add Playwright tests by yourself in the pipeline on your current environment.

1. Create a build.yml file in the .github/workflows directory
2. You always start your YAML file with the name of your worklfow for example: `name: CI`
3. Next, you define a trigger for when your workflow that contains jobs (more on that later) needs to start.
   You can create it as follows: 
   
   `on: push`
   
   This means that whenever new changes are pushed, the workflow will be triggered.

4. Next, you need to create a job. A workflow contain at least one [job](https://docs.github.com/en/actions/using-workflows/workflow-syntax-for-github-actions#jobs).
5. You can create a job as follows:

![Create a job](./images/CI_create_job.png)

Here we have created a job and gave it a display friendly name. This name will be shown in the UI in Github Actions.

6. The squiggly red line below run_tests is shown, because we also need to define on what machine the job will run. We will use a widely used value for this:

![Runs on](./images/CI_runs_on.png) 

Now the squiggly line is gone.

7. A job will not do much until it has steps defined. Steps run in sequence and are defined with the `steps` keyword. Let's define a first step:
![First step](./images/CI_checkout.png)

In the above snipped, we name the step with a display friendly name and we use the predefined Github action `actions/checkout@v3`, which gives the command to checkout (fetch) our code from Github.

8. Now that we have our code we can continue by setting up our environment. Recall from the course prerequisites that you need NodeJS on your system. We need the same to be true for the machine for which the workflow runs. We can do this as follows:
![Install Node)](./images/CI_setup_node.png)

Here we tell the machine to install a version of node that we specify in the `node-version` field. We do this with the `setup-node@v3` action. This action executes all the necessary commands needed to setup node.

9.  Before we can run our tests, we need to first install the dependencies. Recall that this was also true the first time we opened this repository locally with the `npm i` command.

![Install dependencies](./images/CI_install_dependencies.png)

Notice that we are using `npm ci`. How is this different from `npm i`? For now, remember that ci is the optimized version used in continuous integration environments. For the more detailed answer you can check [HERE](https://docs.npmjs.com/cli/v9/commands/npm-ci#description)

10.  Playwright needs the browsers binaries to be downloaded prior to execute tests. We can do that as follows:

![Install browsers](./images/CI_install_browsers.png)

The `--with-deps` argument allows us to download the dependencies needed to run browsers.  

11.    Now we can run the tests like so:
![Run Playwright](./images/CI_run_playwright.png)
1.  Save the file, commit and push it to the origin.   
