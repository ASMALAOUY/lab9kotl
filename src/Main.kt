fun main() {

    // =========================================
    // SECTION 1 : Création du type de compte
    // =========================================

    println("Welcome to your banking system.")
    println("Select the type of bank account you want to open:")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    // Variable qui va stocker le type de compte choisi
    var accountType = ""

    // Variable utilisée pour stocker le choix de l'utilisateur
    var userChoice = 0

    // Boucle qui continue tant que le type de compte n'est pas défini
    while (accountType == "") {

        println("Please choose 1, 2 or 3")

        // Simulation d’un choix utilisateur aléatoire
        userChoice = (1..5).random()

        println("Selected option: $userChoice")

        // Attribution du type de compte selon le choix
        when (userChoice) {
            1 -> accountType = "debit"
            2 -> accountType = "credit"
            3 -> accountType = "checking"
            else -> continue
        }
    }

    println("Account created successfully: $accountType account")



    // =========================================
    // SECTION 2 : Initialisation du solde
    // =========================================

    // Génération d’un solde aléatoire
    var accountBalance = (0..1000).random()

    // Si le compte est un compte de crédit, le solde devient négatif (dette)
    if (accountType == "credit") {
        accountBalance = -(0..1000).random()
        println("Credit account: your balance represents a debt = $accountBalance dollars")
    }

    println("Current balance: $accountBalance dollars")



    // Montant aléatoire utilisé pour les opérations
    val money = (0..1000).random()

    println("Transaction amount: $money dollars")



    // =========================================
    // SECTION 3 : Fonctions bancaires
    // =========================================

    // Fonction qui effectue un retrait
    fun withdraw(amount: Int): Int {
        accountBalance -= amount
        println("Withdrawal successful: $amount dollars removed.")
        println("New balance: $accountBalance dollars")
        return amount
    }

    // Retrait spécifique pour compte debit
    fun debitWithdraw(amount: Int): Int {

        if (accountBalance == 0) {
            println("Withdrawal impossible: balance is zero")
            return accountBalance

        } else if (amount > accountBalance) {
            println("Withdrawal refused: insufficient balance")
            println("Available balance: $accountBalance")
            return 0

        } else {
            return withdraw(amount)
        }
    }

    // Fonction de dépôt d'argent
    fun deposit(amount: Int): Int {
        accountBalance += amount
        println("Deposit completed: $amount dollars added.")
        println("Updated balance: $accountBalance dollars")
        return amount
    }

    // Dépôt spécifique pour les comptes crédit
    fun creditDeposit(amount: Int): Int {

        if (accountBalance == 0) {
            println("Credit already fully paid.")
            return 0

        } else if (accountBalance + amount > 0) {
            println("Deposit rejected: payment exceeds remaining debt.")
            println("Current debt: $accountBalance")
            return 0

        } else if (amount == -accountBalance) {
            accountBalance = 0
            println("Congratulations! The credit is now fully paid.")
            return amount

        } else {
            return deposit(amount)
        }
    }



    // =========================================
    // SECTION 4 : Gestion des transactions
    // =========================================

    fun transfer(mode: String) {

        val transferAmount: Int

        when (mode) {

            // Cas du retrait
            "withdraw" -> {

                transferAmount =
                    if (accountType == "debit")
                        debitWithdraw(money)
                    else
                        withdraw(money)

                println("Withdrawn amount: $transferAmount dollars")
            }

            // Cas du dépôt
            "deposit" -> {

                transferAmount =
                    if (accountType == "credit")
                        creditDeposit(money)
                    else
                        deposit(money)

                println("Deposited amount: $transferAmount dollars")
            }

            else -> return
        }
    }



    // =========================================
    // SECTION 5 : Menu principal du système
    // =========================================

    var systemRunning = true
    var option = 0

    while (systemRunning) {

        println()
        println("===== BANK MENU =====")
        println("1. Show balance")
        println("2. Withdraw money")
        println("3. Deposit money")
        println("4. Exit application")

        println("Choose an option:")

        // Simulation d'un choix utilisateur
        option = (1..5).random()

        println("Selected option: $option")

        when (option) {

            // Afficher le solde
            1 -> println("Your balance is $accountBalance dollars")

            // Retirer de l'argent
            2 -> transfer("withdraw")

            // Déposer de l'argent
            3 -> transfer("deposit")

            // Fermer le programme
            4 -> {
                systemRunning = false
                println("Application closed")
            }

            else -> continue
        }
    }
}