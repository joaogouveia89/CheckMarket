package io.github.joaogouveia89.checkmarket.core.util.ktx

import java.util.Locale


fun Double.asPriceFormat() = String.format(Locale.ROOT, "%.2f", this)