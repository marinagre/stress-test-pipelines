package webHost

import com.typesafe.scalalogging.LazyLogging

/**
  * Created by Yuval.Itzchakov on 12/03/2018.
  */
trait Logger extends LazyLogging {
  def trace(doLog: Boolean, actionName: => String, message: => String): Unit = {
    if (doLog) {
      logger.trace(formatMessage(actionName, message))
    }
  }

  def trace(actionName: => String, message: => String): Unit = {
    logger.trace(formatMessage(actionName, message))
  }

  def info(doLog: Boolean, actionName: => String, message: => String): Unit = {
    if (doLog) {
      logger.info(formatMessage(actionName, message))
    }
  }

  def info(actionName: => String, message: => String): Unit = {
    logger.info(formatMessage(actionName, message))
  }

  def warn(doLog: Boolean, actionName: => String, message: => String): Unit = {
    if (doLog) {
      logger.warn(formatMessage(actionName, message))
    }
  }

  def warn(actionName: => String, message: => String): Unit = {
    logger.warn(formatMessage(actionName, message))
  }

  def error(actionName: => String, message: => String): Unit = {
    logger.error(formatMessage(actionName, message))
  }

  def error(actionName: => String, message: => String, t: Throwable): Unit = {
    logger.error(formatMessage(actionName, message), t)
  }

  def error(actionName: => String, t: Throwable): Unit = {
    logger.error(formatMessage(actionName, ""), t)
  }

  private def formatMessage(actionName: String, message: String) = {
    actionName + ": " + message
  }
}
