# Using Refined with Doobie

This repository is a trivial showcase on how to use Doobie with Refined

## Setup 

This example requires a database. To set up local database in docker simply run `./start-db.sh`. It will launch a docker instance of postgres exposed on `5432` bootstrapped with contents of the `init.sql` file.

## Usage

Simply run the code using [scala-cli](http://scala-cli.virtuslab.org/) (for install instructions visit https://scala-cli.virtuslab.org/install):

```bash
scala-cli run .
```

## Links

- [Refined](https://github.com/fthomas/refined)
- [Doobie](https://tpolecat.github.io/doobie/)