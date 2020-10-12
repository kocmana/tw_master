package at.technikum.masterproject.integrationservice.config;

import graphql.ExecutionResult;
import graphql.execution.ExecutionContext;
import graphql.execution.instrumentation.DeferredFieldInstrumentationContext;
import graphql.execution.instrumentation.ExecutionStrategyInstrumentationContext;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationDeferredFieldParameters;
import graphql.execution.instrumentation.parameters.InstrumentationExecuteOperationParameters;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionStrategyParameters;
import graphql.execution.instrumentation.parameters.InstrumentationFieldCompleteParameters;
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters;
import graphql.execution.instrumentation.parameters.InstrumentationFieldParameters;
import graphql.execution.instrumentation.parameters.InstrumentationValidationParameters;
import graphql.language.Document;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.validation.ValidationError;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInstrumentation extends SimpleInstrumentation {

  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {
    return super.beginExecution(parameters);
  }

//  @Override
//  public InstrumentationContext<ExecutionResult> beginSubscribedFieldEvent(InstrumentationFieldParameters parameters) {
//    return null;
//  }

//  @Override
//  public ExecutionInput instrumentExecutionInput(ExecutionInput executionInput,
//      InstrumentationExecutionParameters parameters) {
//    return null;
//  }
//
//  @Override
//  public DocumentAndVariables instrumentDocumentAndVariables(DocumentAndVariables documentAndVariables,
//      InstrumentationExecutionParameters parameters) {
//    return null;
//  }

  @Override
  public InstrumentationContext<Document> beginParse(InstrumentationExecutionParameters parameters) {
    return super.beginParse(parameters);
  }

  @Override
  public InstrumentationContext<List<ValidationError>> beginValidation(InstrumentationValidationParameters parameters) {
    return super.beginValidation(parameters);
  }

  @Override
  public ExecutionStrategyInstrumentationContext beginExecutionStrategy(
      InstrumentationExecutionStrategyParameters parameters) {
    return super.beginExecutionStrategy(parameters);
  }

  @Override
  public DeferredFieldInstrumentationContext beginDeferredField(InstrumentationDeferredFieldParameters parameters) {
    return super.beginDeferredField(parameters);
  }

  @Override
  public InstrumentationContext<ExecutionResult> beginExecuteOperation(
      InstrumentationExecuteOperationParameters parameters) {
    return super.beginExecuteOperation(parameters);
  }

  @Override
  public InstrumentationContext<ExecutionResult> beginField(InstrumentationFieldParameters parameters) {
    return super.beginField(parameters);
  }

  @Override
  public InstrumentationContext<Object> beginFieldFetch(InstrumentationFieldFetchParameters parameters) {
    return super.beginFieldFetch(parameters);
  }

  @Override
  public InstrumentationContext<ExecutionResult> beginFieldComplete(InstrumentationFieldCompleteParameters parameters) {
    return super.beginFieldComplete(parameters);
  }

  @Override
  public InstrumentationContext<ExecutionResult> beginFieldListComplete(
      InstrumentationFieldCompleteParameters parameters) {
    return super.beginFieldListComplete(parameters);
  }

  @Override
  public GraphQLSchema instrumentSchema(GraphQLSchema schema, InstrumentationExecutionParameters parameters) {
    return super.instrumentSchema(schema, parameters);
  }

  @Override
  public ExecutionContext instrumentExecutionContext(ExecutionContext executionContext,
      InstrumentationExecutionParameters parameters) {
    return super.instrumentExecutionContext(executionContext, parameters);
  }

  @Override
  public DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher,
      InstrumentationFieldFetchParameters parameters) {
    return super.instrumentDataFetcher(dataFetcher, parameters);
  }

  @Override
  public CompletableFuture<ExecutionResult> instrumentExecutionResult(ExecutionResult executionResult,
      InstrumentationExecutionParameters parameters) {
    return super.instrumentExecutionResult(executionResult, parameters);
  }
}
